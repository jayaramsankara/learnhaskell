module RoverM (   RoverM(..), doOption1 , Position(..), Direction(..)) where

  import Rover( Position(..), Direction(..), Command(Move, Turn), move, turn, command )

  newtype RoverM a = RoverData { pos :: a } deriving (Show )

  instance Monad RoverM  where
    return  = pure
    (>>) r r'  =
              let
                rpos = pos r
                r'pos = pos r'
              in
                RoverData r'pos

    (>>=) r f = f (pos r)
  instance Applicative RoverM where
    pure = RoverData
    (<*>) rf ra = RoverData (  pos rf (pos ra) )
  instance Functor RoverM where
    fmap f ra = RoverData ( f (pos ra))

  -- rover :: Position -> RoverM Position
  -- rover = return
  --
  -- pos :: RoverM Position -> Position
  -- pos

  runRover :: Command ->  RoverM Position ->  RoverM Position
  runRover cmd rpos =
          case cmd of
           Move distance ->  return (move (pos rpos)  distance)
           Turn turnDir  ->  return (turn (pos rpos) turnDir)
           _  ->  rpos

  foldFun :: RoverM Position -> (RoverM Position -> RoverM Position) -> RoverM Position
  foldFun rpos f  =  f  rpos

  doOption1 :: RoverM Position -> IO (RoverM Position)
  doOption1  pos = do
       putStrLn "Enter the command string : "
       cmd <- getLine
       let cmdToRun = command cmd
       print (show cmdToRun)
       let posfs  = fmap runRover cmdToRun
       let finalPos = foldl foldFun pos posfs
       print (show finalPos)
       doOption1 (finalPos)
