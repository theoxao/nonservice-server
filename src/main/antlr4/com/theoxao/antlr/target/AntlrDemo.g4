grammar AntlrDemo ;

@members{
    enum Kip{
        YES,NO
    };
    Kip kip = Kip.YES;

    public void noSkip(){
       kip=Kip.NO;
    }

    public void doSkip(){
       kip=Kip.YES;
    }
}

compilationUnit
    :   ROOT   TRUNK WS LEAF* ;

ROOT :  'root'  ;

TRUNK: {noSkip();} 'trunk' ;

LEAF : 'leaf' {doSkip();}   ;

WS  : '.'  { kip==Kip.YES }?->skip;

LINE  :  [\t\r\n\u000C]+ -> skip
    ;
