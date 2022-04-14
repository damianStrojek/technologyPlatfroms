using System.IO;

namespace Lab_1 {
	public abstract class DiskElement {
		protected FileSystemInfo file;

		protected internal abstract void Print(int depth);
		protected abstract string Format(int depth);

		public FileSystemInfo File{
			get {
				return file;
			}
		}

		public void Print() { Print(0); }
	}
}
