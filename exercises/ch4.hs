halve :: [a] -> ([a],[a])

halve x = splitAt hsize x
          where hsize = ((length x) `div` 2)

safetail1 :: [a] -> [a]

safetail1 x = if (null x) then [] else tail x 

safetail2 :: [a] -> [a]

safetail2 x |  null x  = []
            | otherwise = tail x

safetail3 :: [a] -> [a]

safetail3 [] =  []
safetail3 xs = tail xs

v :: Bool -> Bool -> Bool

v False False = False
v _ _ = True

t1 :: Bool -> Bool -> Bool
t1 a b = if a then b else False

t2 :: Bool -> Bool -> Bool
t2 a b = if a  then if b then a else False  else False

mult :: Int -> Int -> Int -> Int
mult a b c = (\x->(\y->(\z-> x*y*z))) a b c 

