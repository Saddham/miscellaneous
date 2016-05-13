#include<bits/stdc++.h>
 
using namespace std;

vector <struct bst> v1;
long long int count = 0l;

struct bst
{
    long long int data;
    int leftIdx;
    int rightIdx;
};

void MakeNode(vector<struct> &v1, long long int aData)
{
    struct bst b1 = { aData, -1, -1 };
    v1.push_back(b1);
}


void setleft(vector <struct>&v1, int currIndex, long long int aData)
{
    unsigned int leftIndex = v1.size();
    v1[currIndex].leftIdx = leftIndex;
    struct bst b1 = { aData, -1, -1 };
    v1.push_back(b1);
}

void setright(vector<struct> &v1, int currIndex, long long int aData)
{
    unsigned int rightIndex = v1.size();
    v1[currIndex].rightIdx = rightIndex;
    struct bst b1 = { aData, -1, -1 };
    v1.push_back(b1);
}

void Insert(long long int aData)
{
    unsigned int currentIdx = 0;
    while ( currentIdx < v1.size() )
    {	count++;
        if(aData <= v1[currentIdx].data)
        {
            if( v1[currentIdx].leftIdx == -1)
            {
                setleft(v1, currentIdx, aData);
                break;
            }
            else
                currentIdx = v1[currentIdx].leftIdx;
        }
        else
        {
            if(v1[currentIdx].rightIdx == -1)
            {
                setright(v1, currentIdx, aData);
                break;
            }
            else
                currentIdx = v1[currentIdx].rightIdx;
        }
    }
}

int main()
{
	int n;
	long long int data;
	
	scanf("%d", &n);
	for(int i=0; i<n; i++){
		scanf("%ll", &data);
		if(i==0){
			MakeNode(v1, data);
			printf("%ll\n", count);
			continue;
		}
		
		Insert(data);
		printf("%ll\n", count);
	}
	
    return 0;
}