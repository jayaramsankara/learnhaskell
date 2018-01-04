module RoverM  where

  import Rover( Position, move, turn)

  newtype RoverM a = RoverData { pos :: a } deriving (Show )

  instance Monad RoverM  where
    return  = pure
    (>>) r r'  =  RoverData ( pos r')
    (>>=) r f = f (pos r)
  instance Applicative RoverM where
    pure = RoverData
    (<*>) rf ra = RoverData (  pos rf (pos ra) )
  instance Functor RoverM where
    fmap f ra = RoverData ( f (pos ra))

--run Position a => :: RoverM a -> Command -> RoverM a
--run rover cmd =
          --case cmd of
          --  Move distance ->   pos >>= (`move` distance)
          --  Turn turnDir  ->   pos >>= (`turn` turnDir)
