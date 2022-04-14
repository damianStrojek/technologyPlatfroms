using System;
using System.IO;

namespace Lab_1{
	class Program{
		static void Main(string[] Args){
			if (Args.Length < 1){
				Console.Error.WriteLine("Error: no input path given.");
				return;
			}
			else if (Directory.Exists(Args[0]) == false){
				Console.Error.WriteLine("Error: path incorrect.");
				return;
			}

			DiskDirectory dd = new DiskDirectory(new DirectoryInfo(Args[0]));
			dd.Print();

			Console.WriteLine("Input enter to terminate");
			Console.ReadLine();
		}
	}
}
