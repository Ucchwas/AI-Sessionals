#include <bits/stdc++.h>
using namespace std;

class Node
{
    public:
    double x;
    double y;
    int nodeNo;
    int ind;


    double distance;
    Node() {}
    Node(int serial, double a, double b)
    {
        x = a;
        y = b;
        nodeNo = serial;
    }
    int getNodeNo()
    {
        return nodeNo;
    }
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }
    double dist(Node other)
    {
        distance = sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
        // cout<<" $ = "<<distance<<endl;
        return distance;
    }
};

class Compare
{
  public:
    bool operator()(Node left, Node right) { return left.distance > right.distance; }
};

vector<int> path;
Node points[100];
int n;
bool visited[100];
double costNNH[5];

double getCost(vector<int> path)
{
    double _cost = 0.0;
    int size = path.size();
    for (int i = 0; i < size; i++)
    {
        int xx = path[i];
        int yy = path[(i + 1) % size];
        _cost += points[xx].dist(points[yy]);
        //cout << _cost << endl;
    }
    return _cost;
}

void printNNH()
{
    cout << "-----------------------------------------------------------" << endl
         << "PATH: ";
    for (int i = 0; i < path.size(); i++)
    {
        cout << path[i] + 1 << " ";
    }
    cout << endl;
    cout << "COST: ";
    int cost = getCost(path);
    cout << cost << endl;
}

double NNH(int initialNode)
{
    path.clear();
    memset(visited, 0, sizeof(visited));

    int currentNode = initialNode;
    // path.push_back(initialNode);

    while (path.size() < n)
    {
        double Min = LLONG_MAX;
        int Idx = -1;
        for (int i = 0; i < n; i++)
        {
            if (visited[i])
                continue;
            if (points[currentNode].dist(points[i]) < Min)
            {
                Min = points[currentNode].dist(points[i]);
                Idx = i;
            }
        }
        visited[Idx] = 1;
        path.push_back(Idx);
        currentNode = Idx;
    }
    //printNNH();
    double cost = getCost(path);

    return cost;
}

void taskOne()
{
    srand(time(NULL));
    for (int i = 0; i < 5; i++)
    {
        int initial = rand() % n;
        costNNH[i] = NNH(initial);
    }
    double minCost = LLONG_MAX;
    double maxCost = 0.0;
    double avgCost;

    for (int i = 0; i < 5; i++)
    {
        if (costNNH[i] < minCost)
        {
            minCost = costNNH[i];
        }
        //cout << costNNH[i] << endl;
        if (costNNH[i] > maxCost)
        {
            maxCost = costNNH[i];
        }
    }

    avgCost = (costNNH[0] + costNNH[1] + costNNH[2] + costNNH[3] + costNNH[4]) / 5;
    cout << "-----------------------NNH(Task-1)-------------------------" << endl;
    cout << "Minimum cost = " << minCost << endl;
    cout << "Maximum cost = " << maxCost << endl;
    cout << "Average cost = " << avgCost << endl;
}



int main()
{
    ifstream myfile;
    string line;
    string data;
    int flag = 0;
    int counter = 0;
    int counter2 = 0;
    myfile.open("burma14.tsp");

    while (myfile >> data)
    {
        int serial;
        double a, b;
        if (flag == 1 and data != "EOF")
        {
            counter++;
            if (counter % 3 == 1)
            {
                serial = stoi(data);
                //cout << serial << " ";
            }
            else if (counter % 3 == 2)
            {
                a = stod(data);
                //cout << a << " ";
            }
            else if (counter % 3 == 0)
            {
                b = stod(data);
                Node newNode(serial, a, b);
                newNode.ind = counter2;
                points[counter2] = newNode;
                counter2++;
                //cout << b << endl;
            }
        }
        if (data == "NODE_COORD_SECTION")
        {
            flag = 1;
        }
    }

    myfile.close();

    n = counter2;

    taskOne();
    //double dd = NNHForTaskTwo(1);

    return 0;
}
