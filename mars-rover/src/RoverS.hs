module RoverS  where


  import Control.Monad.Trans.State

  data Direction = North | East | South | West  deriving (Show)

  data Position = Position { x :: Int , y :: Int, dir :: Direction } deriving (Show)

  data Turn = LeftTurn | RightTurn deriving (Show)

  data Command = Move Int | Turn Turn  | None deriving (Show)

 -- state as a RoverS
  newtype RoverS  = RoverS { pos :: Position } deriving (Show )



  type RoverData = State RoverS Position
  -- value is Position
  initalPos  = Position 0 0 North
  initialRover = RoverS initalPos

  -- state processing function
  runRover :: Command -> RoverS -> (Position, RoverS)
  runRover cmd rover =
          let
              cp = pos rover
              np =  case cmd of
                  Move dis -> move cp dis
                  Turn dir -> turn cp dir
                  _   ->  cp

              nr = RoverS np
          in
              (np,nr)

  toRover :: Command -> RoverData
  toRover cmd  =  state $ (runRover cmd)




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

  tr :: Char -> Command
  tr 'M' = Move 1
  tr 'L' = Turn LeftTurn
  tr 'R' = Turn RightTurn
  tr  _ = None



  command :: String -> [Command]
  command  =  map tr


  doRecursive :: RoverS -> IO ()
  doRecursive rover = do
              putStrLn "Enter the Command: "
              cmd <- getLine
              -- let rds = map toRover  (command cmd)
              -- let rovers = foldl   (flip execState) rover rds
              let cmdToRun =   command cmd
              let roverf  =   execState $ sequence (map toRover  cmdToRun)
              let rovers = roverf rover
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
  rovers   =  map toRover . command

  eachLine :: (String -> [RoverData]) -> (String  -> [[RoverData]])
  eachLine f =    \s ->  fmap  f (lines s)
