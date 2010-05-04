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
vibes = withInstrument 386;
organ = withInstrument 19;
guitar = withInstrument 27;
electric = withInstrument 26;
bass = withInstrument 35;
violin = withInstrument 40;
viola = withInstrument 41;
cello = withInstrument 42;
trumpet = withInstrument 56;
trombone = withInstrument 57;
horn = withInstrument 60;
sax = withInstrument 65;
oboe = withInstrument 68;
bassoon = withInstrument 70;
clarinet = withInstrument 71;
flute = withInstrument 73;
panFlute = withInstrument 75;
woodblock = withInstrument 398;
goblins = withInstrument 101;

repeat 0 music = empty;
repeat n music = music & repeat (n-1) music;

--down d = up (-d); -- Causes endless loop


