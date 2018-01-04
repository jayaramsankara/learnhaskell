module Rover
    (  apply, move, turn, Command(..), Position(..), Direction (..), Turn(..)
    ) where



data Direction = North | East | South | West  deriving (Show)

data Position = Position { x :: Int , y :: Int, dir :: Direction } deriving (Show)
newtype Error = Error String deriving (Show)
data Turn = LeftTurn | RightTurn deriving (Show)

data Command = Move Int | Turn Turn deriving (Show)

move :: Position -> Int -> Either  Error Position
move pos dis  =
      case dir pos of
        East  -> Right (pos { x = x pos + dis })
        West  -> Right (pos { x = x pos - dis })
        North -> Right (pos { y = y pos + dis })
        South -> Right (pos { y = y pos - dis })

turn :: Position -> Turn -> Either  Error Position
turn pos turn  = Right (pos { dir = dirTo ( dir pos ) turn })

dirTo :: Direction -> Turn -> Direction
dirTo East LeftTurn   = North
dirTo East RightTurn  = South
dirTo West LeftTurn   = South
dirTo West RightTurn  = North
dirTo North LeftTurn  = West
dirTo North RightTurn = East
dirTo South LeftTurn  = East
dirTo South RightTurn = West

apply :: Either Error Position -> Command -> Either Error Position
apply pos cmd =
      case cmd of
        Move distance ->   pos >>= (`move` distance)
        Turn turnDir  ->   pos >>= (`turn` turnDir)
