using System;
using System.IO;

namespace Lab_1 {
	public static class FileExtensions {
		public static string GetDOSAttributes(this FileSystemInfo fsi) {
			string[] c = { "-", "r", "a", "h", "s" };
			return c[1 * (fsi.Attributes.HasFlag(FileAttributes.ReadOnly) ? 1 : 0)] +
					c[2 * (fsi.Attributes.HasFlag(FileAttributes.Archive) ? 1 : 0)] +
					c[3 * (fsi.Attributes.HasFlag(FileAttributes.Hidden) ? 1 : 0)] +
					c[4 * (fsi.Attributes.HasFlag(FileAttributes.System) ? 1 : 0)];
		}

		public static DateTime GetOldestFile(this DirectoryInfo dir){
			DateTime result = DateTime.MinValue;
			foreach (var e in dir.EnumerateFileSystemInfos()){
				if (e.GetType() == typeof(DirectoryInfo)){
					DateTime tmp = GetOldestFile(e as DirectoryInfo);
					if (tmp > result)
						result = tmp;
				}
				else if (e.GetType() == typeof(FileInfo))
				{
					if (e.CreationTime > result)
						result = e.CreationTime;
				}
			}
			return result;
		}
	}
}
