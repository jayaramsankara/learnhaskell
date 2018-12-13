module Rover
    (  doOption0, doOption1 , move, turn,
    command, Command(..), Position(..), Direction (..),
     Turn(..)
    ) where



data Direction = North | East | South | West  deriving (Show)

data Position = Position { x :: Int , y :: Int, dir :: Direction } deriving (Show)
newtype Error = Error String deriving (Show)
data Turn = LeftTurn | RightTurn deriving (Show)

data Command = Move Int | Turn Turn  | None deriving (Show)

-- type RoverPosition = Either  Error Position

move :: Position -> Int -> Position
move pos dis  =
      case dir pos of
        East  ->  (pos { x = x pos + dis })
        West  ->  (pos { x = x pos - dis })
        North ->  (pos { y = y pos + dis })
        South ->  (pos { y = y pos - dis })

turn :: Position -> Turn -> Position
turn pos turn  =  (pos { dir = dirTo ( dir pos ) turn })

dirTo :: Direction -> Turn -> Direction
dirTo East LeftTurn   = North
dirTo East RightTurn  = South
dirTo West LeftTurn   = South
dirTo West RightTurn  = North
dirTo North LeftTurn  = West
dirTo North RightTurn = East
dirTo South LeftTurn  = East
dirTo South RightTurn = West

operate ::  Position -> Command -> Position
operate    pos  cmd =
      case cmd of
        Move distance ->   move pos distance
        Turn turnDir  ->   turn pos turnDir
        _ -> pos

tr :: Char -> Command
tr 'M' = Move 1
tr 'L' = Turn LeftTurn
tr 'R' = Turn RightTurn
tr  _ = None



command :: String -> [Command]
command  =  map tr

doOption0 :: Position -> IO String
doOption0  pos = do
    putStrLn "Enter the command char : "
    cmd <- getChar
    let cmdToRun =  tr cmd
    print (show cmdToRun)
    let result =  Rover.operate pos cmdToRun
    print (show result)

    return (show result)
    doOption0 result

doOption1 :: Position -> IO String
doOption1  pos = do
    putStrLn "Enter the command string : "
    cmd <- getLine
    let cmdToRun =  command cmd
    print (show cmdToRun)
    let result = foldl  Rover.operate pos cmdToRun
    print (show result)

    return (show result)
    doOption1 result
