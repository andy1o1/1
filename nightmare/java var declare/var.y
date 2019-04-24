%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int yylex();
FILE *yyin;
void yyerror(char*);
%}

%token TYPE AS NAME VAL SC

%%

start: type1|type2;
type1: TYPE NAME SC 	{printf("\n valid simple declaration "); return 0;}
type2: TYPE NAME AS VAL SC	{printf("\n valid complex declaration "); return 0;}

%%
void yyerror(char *str)
{
	printf("\n invalid declaration : %s",str);
}
int main()
{
	yyin=fopen("input.txt","r");
	yyparse();
	return 0;
} 
