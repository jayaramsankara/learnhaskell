module Main where

import           RoverS

import           Control.Monad.Trans.State

--import Rover

initialRover = RoverS initalPos
initialPosition = Right (Position 0 0 North)

main :: IO String
main = doOption2

doOption2 :: IO String
doOption2 = do
    putStrLn "Command : "
    cmd <- getLine
    let cmdToRun =  reverse $ command cmd
    let result =  foldr  execState initialRover (map runRover  cmdToRun) -- [State RoverS Position]
    print (show  result)
    return ( show result)


--doOption1 :: IO String
--doOption1 = do
    --putStrLn "Enter the command string : "
    --cmd <- getLine
    --let cmdToRun =  reverse $ command cmd
    --let result = foldr (flip Rover.apply ) initialPosition cmdToRun
    --print (show result)
    --return (show result)

tr :: Char -> Command
tr 'M' = Move 1
tr 'L' = Turn LeftTurn
tr 'R' = Turn RightTurn



command :: String -> [Command]
command  =  map tr
