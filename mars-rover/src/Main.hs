module Main where

import           RoverS

import           Control.Monad.Trans.State



--import Rover

type RoverData = State RoverS Position

initialRover = RoverS initalPos
initialPosition = Right (Position 0 0 North)

main :: IO ()
main = doRecursive initialRover



--doOption1 :: IO String
--doOption1 = do
    --putStrLn "Enter the command string : "
    --cmd <- getLine
    --let cmdToRun =  reverse $ command cmd
    --let result = foldr (flip Rover.apply ) initialPosition cmdToRun
    --print (show result)
    --return (show result)

doRecursive :: RoverS -> IO ()
doRecursive rover = do
            putStrLn "Enter the Command: "
            cmd <- getLine
            let rds = map runRover  (command cmd)
            let rovers = foldl   (flip execState) rover rds
            putStr "Rover is at : "
            putStrLn  ( (show . pos) rovers )
            doRecursive rovers



doLazyMoves :: IO RoverS
doLazyMoves = do
    putStrLn "Enter the commands in separate lines: "
    commands <- getContents
    evalRoverDataLines (eachLine rovers commands)

evalRoverDataForALine :: IO RoverS -> [RoverData]  -> IO RoverS
evalRoverDataForALine  iorovers roverDatas  =   do
                                     rovers  <- iorovers
                                     let finalRoverInLine = foldl (flip execState) rovers roverDatas
                                     putStrLn (show finalRoverInLine)
                                     return finalRoverInLine

evalRoverDataLines :: [[RoverData]] -> IO RoverS
evalRoverDataLines = foldl  evalRoverDataForALine (return (initialRover))

rovers :: String  -> [ RoverData ]
rovers   =  map runRover . command

eachLine :: (String -> [RoverData]) -> (String  -> [[RoverData]])
eachLine f =    \s ->  fmap  f (lines s)


tr :: Char -> Command
tr 'M' = Move 1
tr 'L' = Turn LeftTurn
tr 'R' = Turn RightTurn
tr  _ = None



command :: String -> [Command]
command  =  map tr
