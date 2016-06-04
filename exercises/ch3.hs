-- :type ['a','b','c'] = [char]  
-- :type ('a','b','c') = (char,char,char)
-- :type [(False,'O'),(True,'1')] = [(Boolean,Char)]
-- :type ([False,True],['0','1']) = ([Bool],[Char])
-- :type [tail, init, reverse] = [ [a] -> [a] ]

second :: [a] -> a
second xs = head (tail xs)

swap :: (a,b) -> (b,a)
swap (x,y) = (y,x)

pair :: a -> b -> (a,b)

pair x y = (x,y)

double :: Int -> Int

double x = x*2

palindrome :: (Eq a)=> [a]  -> Bool

palindrome xs = (reverse xs) == xs

twice :: (b->b)->b->b
twice f x = f(f x)
