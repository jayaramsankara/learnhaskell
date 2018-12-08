module Main where

import System.Random
import Control.Applicative
import Control.Monad.Trans.State


main :: IO ()
main = do
        print "Option1"
        (a1,b1) <- rollDiceIO
        print (show (a1,b1))
        print "Option2"
        as <- rollNDiceIO 10
        print (show as)
        print "Option3"
        let (a2,b2) = clumsyRollDice
        print (show (a2,b2))
        print "Option4"
        let (t,g) = rollTDice (mkStdGen 1)
        print (show (t,g))
        print "Option5"
        let (a3,b3) = (evalState rollDice) (mkStdGen 1)
        print (show (a3,b3))
        print "Option6"
        let (a4) = (evalState (rollNDice 10)) (mkStdGen 1)
        print (show (a4))
        return()



rollDiceIO ::  IO (Int, Int)
rollDiceIO = liftA2 (,) (randomRIO (1,6)) (randomRIO (1,6))

rollNDiceIO :: Int -> IO [Int]
rollNDiceIO cnt =
  let
    rios =  fmap (\_ -> randomRIO (1,6) ) [1 .. cnt]
  in
    sequence rios


clumsyRollDice :: (Int, Int)
clumsyRollDice = (n, m)
        where
        (n, g) = randomR (1,6) (mkStdGen 666)
        (m, _) = randomR (1,6) g

rollTDice :: StdGen -> ((Int, Int), StdGen)
rollTDice  g = ((n,m),g')
          where
            (n, g) = randomR (1,6) (mkStdGen 666)
            (m, g') = randomR (1,6) g

rollDie :: State StdGen Int
rollDie = state $ randomR (1,6)

rollDice :: State StdGen (Int, Int)
rollDice = liftA2 (,) rollDie rollDie

rollNDice :: Int -> State StdGen [Int]
rollNDice cnt = sequence  (fmap (\_ -> rollDie) [1 .. cnt])
