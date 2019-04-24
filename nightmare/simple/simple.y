%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int yylex();
FILE *yyin;
void yyerror(char*);
%}

%token SUB VERB OBJ CON

%%
start: simple|compound;
simple: SUB VERB OBJ {printf("\n VALID SIMPLE STMT"); return 1;}
compound: SUB VERB OBJ CON SUB VERB OBJ 	{printf("\n VALID compound STMT"); return 1;}
%%

void yyerror(char *str)
{
	printf("\n not valid stmt : %s",str); 
} 
int main()
{
	yyin=fopen("input.txt","r");
	yyparse();
	return 0;
}
