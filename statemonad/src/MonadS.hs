module MonadS

where

import Control.Applicative
import Control.Monad.Trans.State
import System.Random
import Data.List

rollDiceIO :: IO (Int, Int)
rollDiceIO = liftA2 (,) (randomRIO (1,6)) (randomRIO (1,6))


rollNDiceIO :: Int ->  IO [Int]
rollNDiceIO num =  sequence  (replicate num (randomRIO (1,6)) )


clumsyRollDice :: (Int, Int)
clumsyRollDice = (n, m)
        where
        (n, g) = randomR (1,6) (mkStdGen 0)
        (m, _) = randomR (1,6) g

rollDice :: StdGen -> ((Int, Int), StdGen)
rollDice gen =  ((firstval,secondval), lastgen)
                where
                      (firstval, secondgen) = randomR (1,6) gen
                      (secondval, lastgen ) = randomR (1,6) secondgen

newtype StateM s a = StateM { runStateM :: s ->  (a,s)}

instance Functor (StateM s) where
  fmap f s = StateM k  where
    p = runStateM s
    q = f . fst . p
    k  = \t -> ((q t) , t)

instance Applicative (StateM s) where
  pure  = \a -> StateM (\s   -> (a,s))
  (<*>) mf sa = StateM k where
    p = fst . runStateM sa
    q = fst . runStateM mf
    k = \s ->  (q s (p s) , s)

instance Monad (StateM s) where
  return = pure
  (>>=) s f  =   StateM $ \s0 ->
            let
                 (a,s1) = runStateM s s0
              in
               runStateM (f a) s1
  --(>>=) s f  =   StateM k
    --   p = fst . runStateM s -- p :: s -> a
    --   q =  f . p  -- q :: s -> StateM s b
    --   k =  \s0 -> (( runStateM . q ) s0) s0


rollDie :: State StdGen Int
rollDie = state $ randomR (1, 6)


rollNDice :: Int -> State StdGen [Int]
rollNDice cnt = sequence (replicate cnt rollDie)

rollNDiceAndShow :: Int -> [Int]
rollNDiceAndShow cnt =  let
                        genfn = runState (rollNDice cnt)
                        g = mkStdGen 10
                    in
                        fst ( genfn g )
