--pl :: [Int] -> Int
pl [] = 1
pl (x:xs) = x * pl xs

--qsort reverse
qsortr :: [Int] -> [Int]
qsortr [] = []
qsortr (x:xs) = qsortr larger  ++ [x] ++ qsortr smaller
               where
                  smaller = [a | a <- xs, a <= x]
                  larger = [b | b <- xs, b > x]
qsort :: [Int] -> [Int]

--qsort 
qsort [] = []
qsort (x:xs) = qsort smaller  ++ [x] ++ qsort larger
               where
                  smaller = [a | a <- xs, a <= x]
                  larger = [b | b <- xs, b > x]

--qsort with duplicates removed
qsortu [] = []
qsortu (x:xs) = qsortu smaller  ++ [x] ++ qsortu larger
               where
                  smaller = [a | a <- xs, a < x]
                  larger = [b | b <- xs, b > x]
