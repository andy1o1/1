%{
#include<stdio.h>
#include<string.h>
%}

%%
[\t ]+     //ignore
[\n]+      //ignore
[a-zA-Z0-9]+     {printf("\n%s ",yytext);}
['{','}','(',')',';','=','"',.]     {printf("\n%s ",yytext);}
"["		{printf("\n%s ",yytext);}
"]"		{printf("\n%s ",yytext);}
%%

yywrap(){}
int main()
{
yyin=fopen("input.txt","r");
yylex();
return 0;
}
