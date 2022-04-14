#include<iostream>
#include<fstream>
#include<cstdlib>
#include<string>
#include<vector>
#include<list>
#include<ctime>
#include<algorithm>
using namespace std;

class CPytanie {
private:
	string poprawna;
	string pytanie;
	vector<string> odpowiedzi;
	int liczba_wyswietlen;
public:
	CPytanie(string pyt) : pytanie(pyt), liczba_wyswietlen(0) {}
	void DodajOdp(string& odp) { odpowiedzi.push_back(odp); }
	void Wyswietlono() { liczba_wyswietlen++; }
	int LiczbaWyswietlen() const { return liczba_wyswietlen; }
	string& Pytanie() { return pytanie; }
	string Poprawna() { return poprawna; }
	void Poprawna(string _poprawna) { poprawna = _poprawna; }
	vector<string>& Odpowiedzi() { return odpowiedzi; }
};

int pyt_sort(const CPytanie& pyt1, const CPytanie& pyt2) {
	return pyt1.LiczbaWyswietlen() < pyt2.LiczbaWyswietlen();
}

string& napraw_ogonki(string& napis) {
#if defined(WINDOWS) || defined(WIN32) || defined(_WIN32)
	int l = napis.size();
	for (int i = 0; i < l; ++i) {
		if (napis[i] < 0) {
			switch (napis[i]) {
			case -54: napis[i] = 168; break;
			case -81: napis[i] = 189; break;
			case -113: napis[i] = 141; break;
			case -58: napis[i] = 143; break;
			case -116: napis[i] = 151; break;
			case -47: napis[i] = 227; break;
			case -45: napis[i] = 224; break;
			case -93: napis[i] = 157; break;
			case -91: napis[i] = 164; break;
			case -71: napis[i] = 165; break;
			case -22: napis[i] = 169; break;
			case -97: napis[i] = 171; break;
			case -26: napis[i] = 134; break;
			case -100: napis[i] = 152; break;
			case -77: napis[i] = 136; break;
			case -65: napis[i] = 190; break;
			case -15: napis[i] = 228; break;
			case -13: napis[i] = 162; break;
			}
		}
	}
#endif
	return napis;
}

int bye(int status = 0) {
	if (status != 0) cout << "BLAD WCZYTYWANIA PLIKU\n";
	cout << "Naura\n";

	string stop;
	getline(cin, stop);

	return status;
}

void wypisz_baze(vector<CPytanie>& pytania) {
	int size = pytania.size();
	for (int i = 0; i < size; i++) {
		cout << i << " " << pytania[i].Pytanie() << endl;
		int lpyt = pytania[i].Odpowiedzi().size();
		for (int j = 0; j < lpyt; j++) cout << pytania[i].Odpowiedzi()[j] << endl;
		cout << pytania[i].Poprawna() << endl;
	}
}

int main(int argc, char* argv[]){
	cout << "Tester ver 1.1" << endl;
	cout << "Lemm @ 2012 in association with DS @ 2022" << endl;
	vector<CPytanie> pytania;
	ifstream plik("baza.dat", ifstream::in);
	string temp, odp, ver;
	int lpytan;

	if (!plik.good()) return bye(1);
	while (plik.good()) {
		getline(plik, temp);
		//nowe pytanie
		if (!temp.empty()) {
			CPytanie nowe(napraw_ogonki(temp));
			plik >> lpytan;

			//wyciecie entera
			getline(plik, temp);
			int i = 0;
			for (i = 0; i < lpytan && plik.good(); i++) {
				getline(plik, temp);
				nowe.DodajOdp(napraw_ogonki(temp));
			}
			if (i == lpytan) {
				plik >> odp;
				getline(plik, temp);
				nowe.Poprawna(odp);
				pytania.push_back(nowe);
			}

		}
	}

	cout << "Odpowiadaj na pytania w formacie 'a', 'ab', 'ac', 'cde', etc.\nNacisnij enter jezeli jestes gotow na 1sze pytanie" << endl;
	string ans;
	getline(cin, ans);
	int nrpyt = 0, poprawne = 0, indeks = 0;
	srand(time(NULL));
	while (true) {
		cout << "Twoj aktualny wynik to: " << (double)((double)poprawne / (double)(nrpyt == 0 ? 1 : nrpyt)) * 100.0f << "% (" << poprawne << "/" << nrpyt << ")\n\n";
		//niech wybiera pytania najmniej razy pokazane, lub ze zlymi odp.

		sort(pytania.begin(), pytania.end(), pyt_sort);
		int los = rand() % (pytania.size()) / 4;

		cout << pytania[los].Pytanie() << endl;
		int lpyt = pytania[los].Odpowiedzi().size();

		for (int i = 0; i < lpyt; i++) cout << (char)(i + 'A') << ") " << pytania[los].Odpowiedzi()[i] << endl;

		getline(cin, ans);
		sort(ans.begin(), ans.end());

		if (ans.compare(pytania[los].Poprawna()) == 0) {
			cout << "Brawo! To byla poprawna odpowiedz" << endl;
			poprawne++;
		}
		else cout << "To niestety zla odpowiedz. Poprawna to: " << pytania[los].Poprawna() << endl;

		pytania[los].Wyswietlono();
		nrpyt++;
		cout << endl;
	}
	bye(0);
	return 0;
}