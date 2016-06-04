--Correct these errors and then check that your script works properly using Hugs-- 1. Changed N to n as N is for data 
-- 2. paranthesis around length xs
-- 3. Using the correct quotes around div ``


n = a `div` (length xs)
    where 
      a = 10
      xs = [1,2,3,4,5]


myl :: [a] -> a
myl a  =  a !! ((length a) - 1)

init1 :: [a] -> [a]
init1 a = take ( (length a) -1 ) a

init2 :: [a] -> [a]
init2 a = reverse (tail (reverse a))  
