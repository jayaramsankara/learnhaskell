module Main where

import           Rover

initalPosition = Right (Position 0 0 Rover.North)

main :: IO String
main = do
    putStrLn "Enter the command string : "
    cmd <- getLine
    let cmdToRun =  reverse $ command cmd
    let result = foldr (flip Rover.apply ) initalPosition cmdToRun
    print (show result)
    return (show result)
    --finalPos <- foldr (\x )

tr :: Char -> Command
tr 'M' = Move 1
tr 'L' = Turn LeftTurn
tr 'R' = Turn RightTurn



command :: String -> [Command]
command  =  map tr


  --print  (show (Rover.apply (Position 1 1 Rover.North) (Turn LeftTurn)))
