module RoverM (   RoverM(..), doOption1 , Position(..), Direction(..), command, runRover) where

  import Rover( Position(..), Direction(..), Command(Move, Turn), move, turn, command )

  newtype RoverM a = RoverM { pos :: a } deriving (Show )

  instance Monad RoverM  where
    return  = pure
    (>>) r r'  =
              let
                rpos = pos r
                r'pos = pos r'
              in
                RoverM r'pos

    (>>=) r f = f (pos r)
  instance Applicative RoverM where
    pure = RoverM
    (<*>) rf ra = RoverM (  pos rf (pos ra) )
  instance Functor RoverM where
    fmap f ra = RoverM ( f (pos ra))



  runRover :: RoverM Position -> Command ->  RoverM Position
  runRover  rpos  cmd =
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
       let posfs  = fmap (flip runRover) cmdToRun
       let finalPos = foldl foldFun pos posfs
       print (show finalPos)
       doOption1 (finalPos)
