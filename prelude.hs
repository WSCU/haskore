-- Primitives 

-- +, -, *, /, unary -
-- == > < <= >= !=
-- || &&
-- faster
-- up
-- withInstrument
-- empty
-- duration
-- rev
-- Add composition - forward to actual prelude.  9r
-- Volume adjustment?


-- WORKING
slower d = faster (1/d);
rest d = slower (d / duration r) r;

dot = slower (3/2);
ddot = slower (7/4);
composefn f g x = f(g x);
dw = dot . w;
w = id;
dh = dot . h;
h = faster (2);
dq = dot . q;
q = faster (4);
de = dot . e;
e = faster (8);
ds = dot . s;
s = faster (16);

--Instruments
piano = withInstrument 0;
harpsichord = withInstrument 6;
vibes = withInstrument 2;
organ = withInstrument 3;
guitar = withInstrument 4;
electric = withInstrument 5;
bass = withInstrument 6;
violin = withInstrument 7;
viola = withInstrument 8;
cello = withInstrument 9;
trumpet = withInstrument 10;
trombone = withInstrument 11;
horn = withInstrument 12;
sax = withInstrument 13;
oboe = withInstrument 14;
bassoon = withInstrument 15;
clarinet = withInstrument 16;
flute = withInstrument 17;
panFlute = withInstrument 18;
kalimba = withInstrument 19;
woodblock = withInstrument 20;
goblins = withInstrument 101;

repeat 0 music = empty;
repeat n music = music & repeat (n-1) music;

--down d = up (-d); -- Causes endless loop


