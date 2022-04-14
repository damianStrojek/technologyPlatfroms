using System;
using System.Collections.Generic;
using System.IO;

namespace Lab_1 {
	public class DiskDirectory : DiskElement {
		IList<DiskElement> children;

		public DiskDirectory(DirectoryInfo dir){
			this.file = dir;
			this.children = new List<DiskElement>();

			foreach (FileSystemInfo f in dir.EnumerateFileSystemInfos()){
				if (f.GetType() == typeof(DirectoryInfo))
					this.children.Add(new DiskDirectory(f as DirectoryInfo));
				else if (f.GetType() == typeof(FileInfo))
					this.children.Add(new DiskFile(f as FileInfo));
			}
		}

		protected override string Format(int depth){
			string name = "";

			for (int i = 0; i < depth; ++i)
				name += '\t';

			name += String.Format("{0} ({1}) {2}", file.Name, ((DirectoryInfo)file).GetFileSystemInfos().Length, file.GetDOSAttributes());
			return name;
		}

		protected internal override void Print(int depth){
			Console.WriteLine(this.Format(depth));

			foreach (DiskElement e in children)
				e.Print(depth + 1);
		}

		public IDictionary<string, int> GetCollection(IComparer<string> cmp){
			SortedDictionary<string, int> sd = new SortedDictionary<string, int>(cmp);
			foreach (var child in children){
				if (child.GetType() == typeof(DirectoryInfo))
					sd.Add(child.File.Name, ((DirectoryInfo)child.File).GetFileSystemInfos().Length);
				else if (child.GetType() == typeof(FileInfo))
					sd.Add(child.File.Name, (int)((FileInfo)child.File).Length);
			}
			return sd;
		}
	}
}
