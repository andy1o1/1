%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "y.tab.h"
%}

%%
he|she|I|me|we|her|his|they|mahesh	{return SUB;}
is|are|do|did|does|read|paint|was|were|be|been|ride  {return VERB;}
book|bike|bat|wall		{return OBJ;}
but|or|while|and 	{return CON;}
%%

int yywrap()
{
	return 1;
}
