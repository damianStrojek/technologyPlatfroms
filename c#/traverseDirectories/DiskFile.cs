using System;
using System.IO;

namespace Lab_1 {
	public class DiskFile : DiskElement {
		public DiskFile(FileInfo file) { this.file = file; }

		protected override string Format(int depth){
			string name = "";

			for (int i = 0; i < depth; ++i)
				name += '\t';

			name += String.Format("{0} {1} bajtow {2}", file.Name, ((FileInfo)file).Length, file.GetDOSAttributes());
			return name;
		}

		protected internal override void Print(int depth){
			Console.WriteLine(this.Format(depth));
		}
	}
}
