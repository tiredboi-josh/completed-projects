#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include<string>
#include <stdlib.h>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string.h>
#include <bits/stdc++.h>
#include <list>
#include <functional>
#include <cctype>

using namespace std;

//csv file of stocks == stocks.csv
// format ==
//stock symbol, open price, closing price, todays high, todays low, prev close, volume

class stock {

public:
	stock(string = "", double = 0, double = 0, double = 0, double = 0, double =
			0, double = 0);
	void printStock();
	double totalStockAsset = 0;
	string getSymbol();
	double getPercent();
	double getValue();

private:
	double value;
	double percentGainCalc();
	string stockSym;
	double todaysOpenVaule;
	double todaysClosing;
	double todaysHigh;
	double todaysLow;
	double prevClose;
	double percentGain;
	double volume;
};

string stock::getSymbol() {

	return stockSym;
}

double stock::getPercent() {

	return percentGain;
}
double stock::getValue() {

	return value;
}

stock::stock(string new_stockSym, double new_todaysOpenVaule,
		double new_todaysClosing, double new_todaysHigh, double new_todaysLow,
		double new_prevClose, double new_volume) {
	stockSym = new_stockSym;
	todaysOpenVaule = new_todaysOpenVaule;
	todaysClosing = new_todaysClosing;
	todaysHigh = new_todaysHigh;
	todaysLow = new_todaysLow;
	prevClose = new_prevClose;
	volume = new_volume;
	totalStockAsset = 0;
	percentGain = percentGainCalc();
	value = volume * todaysClosing;

}

void stock::printStock() {
	cout << setprecision(2) << fixed;
	cout << setw(10) << stockSym;
	cout << setw(17) << todaysOpenVaule;
	cout << setw(15) << todaysClosing;
	cout << setw(12) << todaysHigh;
	cout << setw(11) << todaysLow;
	cout << setw(11) << prevClose;
	cout << setw(13) << percentGain;
	cout << setw(12) << volume;

}

double stock::percentGainCalc() {

	return ((todaysClosing / prevClose) * 100) - 100;

//		return ((prevClose / todaysClosing) * 100) - 100;

}

void printTable() {
	cout << "*********    First Investor's Heaven    *********" << endl;
	cout << "*********       Financial Report       *********" << endl;
	cout << setw(10) << "stockSym";
	cout << setw(17) << "todaysOpenVaule";
	cout << setw(15) << "todaysClosing";
	cout << setw(12) << "todaysHigh";
	cout << setw(11) << "todaysLow";
	cout << setw(11) << "prevClose";
	cout << setw(13) << "percentGain";
	cout << setw(12) << "volume";
	cout << endl;
	cout << setw(10) << "----------";
	cout << setw(17) << "---------------";
	cout << setw(15) << "-------------";
	cout << setw(12) << "----------";
	cout << setw(11) << "---------";
	cout << setw(11) << "---------";
	cout << setw(13) << "-----------";
	cout << setw(12) << "----------";
	cout << endl;

}

vector<stock> alphaSortMeStonks(vector<stock> stonks) {
	int indexlength = stonks.size();
	vector<string> temp;
	temp.resize(indexlength);

	for (unsigned int i = 0; i < stonks.size(); i++) {

		temp[i] = stonks[i].getSymbol();
		//cout << temp[i] << endl;

	}

	std::sort(temp.begin(), temp.end());
	//swaping perpose // help me plz
	stock tempStonk;

	for (unsigned int i = 0; i < temp.size(); i++) {

		for (unsigned int s = 0; s < stonks.size(); s++) {

			if (temp[i] == stonks[s].getSymbol()) {

				tempStonk = stonks[s];
				stonks[s] = stonks[i];
				stonks[i] = tempStonk;

			}

		}

	}

	return stonks;

}

vector<stock> percetGainSortMeStonks(vector<stock> stonks) {
	int indexlength = stonks.size();
	vector<double> temp;
	temp.resize(indexlength);

	for (unsigned int i = 0; i < stonks.size(); i++) {

		temp[i] = stonks[i].getPercent();
		//cout << temp[i] << endl;

	}

	std::sort(temp.begin(), temp.end(), greater<int>());
	//swaping perpose // help me plz
	stock tempStonk;

	for (unsigned int i = 0; i < temp.size(); i++) {

		for (unsigned int s = 0; s < stonks.size(); s++) {

			if (temp[i] == stonks[s].getPercent()) {

				tempStonk = stonks[s];
				stonks[s] = stonks[i];
				stonks[i] = tempStonk;

			}

		}

	}

	return stonks;
}
double getStonksTotalValue(vector<stock> stonks) {

	double totalValue = 0;

	for (unsigned int i = 0; i < stonks.size(); i++) {

		totalValue = totalValue + stonks[i].getValue();
	}

	return totalValue;
}

int main() {
	int totalStonks = 0;
	ifstream stockFile("stocks.csv");
	string line;
	vector<stock> allMeStonks;
	vector<stock> allMeStonksAlphaSort;
	vector<stock> allMeStonksPercentSort;

	while (getline(stockFile, line)) {

		istringstream ss(line);
		string token;
		vector<string> stockLine;
		int count = 0;

		while (getline(ss, token, '\t')) {

			stockLine.resize(count);
			stockLine.push_back(token);
			count++;
		}

		allMeStonks.resize(totalStonks);

		stock temp(stockLine[0], atof(stockLine[1].c_str()),
				atof(stockLine[2].c_str()), atof(stockLine[3].c_str()),
				atof(stockLine[4].c_str()), atof(stockLine[5].c_str()),
				atof(stockLine[6].c_str()));
		allMeStonks.push_back(temp);
		totalStonks++;
		/*
		 for (unsigned int i = 0; i < stockLine.size(); i++) {

		 cout << stockLine[i] << endl;
		 }
		 */
	}
	printTable();
	allMeStonksAlphaSort = allMeStonks;
	allMeStonksAlphaSort = alphaSortMeStonks(allMeStonksAlphaSort);
	allMeStonksPercentSort = allMeStonks;
	allMeStonksPercentSort = percetGainSortMeStonks(allMeStonksPercentSort);

	for (unsigned int i = 0; i < allMeStonks.size(); i++) {

		allMeStonks[i].printStock();
		cout << endl;
	}
	cout << "Closing Assets: $" << getStonksTotalValue(allMeStonks) << endl;
	cout << "*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*"
			<< endl;

	cout << endl << endl << endl << endl;
	cout << "*********       Alphabetically        *********" << endl;
	printTable();

	for (unsigned int i = 0; i < allMeStonksAlphaSort.size(); i++) {

		allMeStonksAlphaSort[i].printStock();
		cout << endl;
	}
	cout << "Closing Assets: $" << getStonksTotalValue(allMeStonksAlphaSort)
			<< endl;

	cout << "*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*"
			<< endl;

	cout << endl << endl << endl << endl;
	cout << "*********       Percent Gain        *********" << endl;
	printTable();

	for (unsigned int i = 0; i < allMeStonksPercentSort.size(); i++) {

		allMeStonksPercentSort[i].printStock();
		cout << endl;
	}

	cout << "Closing Assets: $" << getStonksTotalValue(allMeStonksPercentSort)
			<< endl;
	cout << "*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*"
			<< endl;
	stockFile.close();
	return 0;
}
