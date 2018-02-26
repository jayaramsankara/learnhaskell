module RoverS  where


  import Control.Monad.Trans.State

  data Direction = North | East | South | West  deriving (Show)

  data Position = Position { x :: Int , y :: Int, dir :: Direction } deriving (Show)

  data Turn = LeftTurn | RightTurn deriving (Show)

  data Command = Move Int | Turn Turn  | None deriving (Show)

 -- state as a RoverS
  newtype RoverS  = RoverS { pos :: Position } deriving (Show )

  initalPos  = Position 0 0 North


  -- value is Position

  -- state processing function

  runRover :: Command -> State RoverS Position
  runRover cmd  =  state $ roverProcessor
                   where
                      roverProcessor = \rover ->
                              let
                                  cp = pos rover
                                  np =  case cmd of
                                      Move dis -> move cp dis
                                      Turn dir -> turn cp dir
                                      _   ->  cp

                                  nr = RoverS np
                              in
                                  (np,nr)




  move :: Position -> Int -> Position
  move pos dis =
        case dir pos of
          East  -> pos { x = x pos + dis }
          West  -> pos { x = x pos - dis }
          North -> pos { y = y pos + dis }
          South -> pos { y = y pos - dis }

  turn :: Position -> Turn -> Position
  turn pos turn  =  pos { dir = dirTo ( dir pos ) turn }

  dirTo :: Direction -> Turn -> Direction
  dirTo East LeftTurn   = North
  dirTo East RightTurn  = South
  dirTo West LeftTurn   = South
  dirTo West RightTurn  = North
  dirTo North LeftTurn  = West
  dirTo North RightTurn = East
  dirTo South LeftTurn  = East
  dirTo South RightTurn = West
