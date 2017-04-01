object test {
  ("tommorrow".groupBy((c:Char)=> c) map {case (c, s) => (c, s.length)}).toList
                                                  //> res0: List[(Char, Int)] = List((t,1), (m,2), (r,2), (w,1), (o,3))
  
}