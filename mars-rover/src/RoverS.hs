module RoverS(RoverS(..), doOption1, Position(..), Direction(..))  where


  import Control.Monad.Trans.State
  import Rover( Position(..), Direction(..), Command(Move, Turn), move, turn, command )



 -- state as a RoverS
  newtype RoverS  = RoverS { pos :: Position } deriving (Show )

  type RoverData = State RoverS Position


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

  doOption1 :: RoverS -> IO ()
  doOption1 rover = do
              putStrLn "Enter the Command: "
              cmd <- getLine
              -- let rds = map toRover  (command cmd)
              -- let rovers = foldl   (flip execState) rover rds
              let cmdToRun =   command cmd
              let roverf  =   execState $ sequence (map toRover  cmdToRun)
              let rovers = roverf rover
              putStr "Rover is at : "
              putStrLn  ( (show . pos) rovers )
              doOption1 rovers



  -- doLazyMoves :: IO RoverS
  -- doLazyMoves = do
  --     putStrLn "Enter the commands in separate lines: "
  --     commands <- getContents
  --     evalRoverDataLines (eachLine rovers commands)
  --
  -- evalRoverDataForALine :: IO RoverS -> [RoverData]  -> IO RoverS
  -- evalRoverDataForALine  iorovers roverDatas  =   do
  --                                      rovers  <- iorovers
  --                                      let finalRoverInLine = foldl (flip execState) rovers roverDatas
  --                                      putStrLn (show finalRoverInLine)
  --                                      return finalRoverInLine
  --
  -- evalRoverDataLines :: [[RoverData]] -> IO RoverS
  -- evalRoverDataLines = foldl  evalRoverDataForALine (return (initialRover))
  --
  -- rovers :: String  -> [ RoverData ]
  -- rovers   =  map toRover . command
  --
  -- eachLine :: (String -> [RoverData]) -> (String  -> [[RoverData]])
  -- eachLine f =    \s ->  fmap  f (lines s)
