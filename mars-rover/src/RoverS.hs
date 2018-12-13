module RoverS ( RoverS(..), doOption1, Position(..), Direction(..) )  where


  import Control.Monad.Trans.State
  import Rover( Position(..), Direction(..), Command(..), command,  move, turn )



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

  doOption1 :: RoverS -> IO String
  doOption1 rover = do
              putStrLn "Enter the Command: "
              cmd <- getLine
              let cmdToRun =   command cmd
              let roverf  =   runState $ sequence (fmap toRover  cmdToRun)
              let rovers = roverf rover
              let (poss,rovers) = roverf rover
              putStr "Intermediate Rover Positions :"
              print poss
              putStr "Rover is at : "
              putStrLn  ( (show . pos) rovers )
              doOption1 rovers
