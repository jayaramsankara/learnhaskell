module Main where



-- import Rover

-- import  RoverM

import RoverS

initialPosition =  Position 0 0 North


-- main :: IO String
-- main = Rover.doOption0 initialPosition


-- main :: IO String
-- main = do
--           rovm <- RoverM.doOption1 (return(initialPosition))
--           return $ show (pos rovm)

main :: IO String
main = do
          let  initialRover = RoverS initialPosition
          rpos <- RoverS.doOption1 initialRover
          return (show rpos)
