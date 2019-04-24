%{
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include "y.tab.h"
%}

DIGIT [0-9]
ASSIGN =

%%
int 	{printf("\n %s : int type ",yytext); return TYPE;}
float	{printf("\n %s : float type ",yytext); return TYPE;}
char 	{printf("\n %s : char type ",yytext); return TYPE;}
{ASSIGN} 	{printf("\n %s : assignment ",yytext); return AS;}
[a-zA-Z_]+[a-zA-Z0-9_]+		{printf("\n %s : name ",yytext); return NAME;}
{DIGIT} 	{printf("\n %s : value ",yytext); return VAL;}
";"		{printf("\n %s : semicolon ",yytext); return SC;}

%%
int yywrap()
{
	return 1;
}
