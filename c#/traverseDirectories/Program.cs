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

			string path = Args[0];
			DirectoryInfo directoryInfo = new DirectoryInfo(path);
			DiskDirectory diskDirectory = new DiskDirectory(directoryInfo);
			diskDirectory.Print();
			Console.WriteLine("\nOldest file: {0}", (directoryInfo.GetOldestFile()));
            		CreateCollection(path);

			Console.WriteLine("Input enter to terminate");
			Console.ReadLine();
		}
		
		public static void CreateCollection(string path){
		    SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new StringComparer());
			
		    if (File.Exists(path)){
			FileInfo file = new FileInfo(path);
			collection.Add(file.Name, (int)file.Length);
		    }
		    else if (Directory.Exists(path)){
			DirectoryInfo dir = new DirectoryInfo(path);
			foreach (var subdir in dir.GetDirectories())
			    collection.Add(subdir.Name, (subdir.GetFiles().Length + subdir.GetDirectories().Length));
			foreach (var file in dir.GetFiles())
			    collection.Add(file.Name, (int)file.Length);
		    }
			
		    FileStream fs = new FileStream("DataFile.dat", FileMode.Create);
		    BinaryFormatter formatter = new BinaryFormatter();
			
		    try {
			formatter.Serialize(fs, collection);
		    } catch (SerializationException e) {
			Console.WriteLine("Serialization error: {0}", e.Message);
		    }
		    fs.Close();
		    Deserialize();
		}
		
		public static void Deserialize(){
		    SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new StringComparer());
		    FileStream fs = new FileStream("DataFile.dat", FileMode.Open);
			
		    try {
			BinaryFormatter formatter = new BinaryFormatter();
			collection = (SortedDictionary<string, int>)formatter.Deserialize(fs);
		    } catch (SerializationException e) {
			Console.WriteLine("Serialization error: {0}", e.Message);
		    }

		    foreach (var file in collection)
			Console.WriteLine("{0} -> {1}", file.Key, file.Value);
		}
	}
}
