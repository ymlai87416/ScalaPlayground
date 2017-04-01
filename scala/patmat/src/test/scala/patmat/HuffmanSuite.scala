package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  /*
  test("times of some") {
    new TestTrees {
      var str = "bbkkbbrraraadaa"
      var rlist = makeOrderedLeafList(times(str.toList))
      assert(rlist == List(('a', 5), ('b', 4), ('r', 3), ('k', 2), ('d', 1)).reverse)
    }
  }*/

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("makeCodeTree") {
    assert(makeCodeTree(Leaf('e', 1), Leaf('t', 2)) === Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quickencode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("Encode and quick Encode: same result") {
    new TestTrees {
      val input = "Beginning with a reference to the Emancipation Proclamation, which freed millions of slaves in 1863,[2] King observes that: one hundred years later, the Negro still is not free.[3] Toward the end of the speech, King departed from his prepared text for a partly improvised peroration on the theme I have a dream, prompted by Mahalia Jackson's cry: Tell them about the dream, Martin![4] In this part of the speech, which most excited the listeners and has now become its most famous, King described his dreams of freedom and equality arising from a land of slavery and hatred.[5] Jon Meacham writes that, With a single phrase, Martin Luther King, Jr. joined Jefferson and Lincoln in the ranks of men who've shaped modern America.[6] The speech was ranked the top American speech of the 20th century in a 1999 poll of scholars of public address.[7]"
      val codeTree = createCodeTree(input.toList)
      assert(encode(codeTree)(input.toList) == quickEncode(codeTree)(input.toList))
    }
  }
  
  test("Encode with create code tree: normal encode") {
    new TestTrees {
      val input = "Beginning with a reference to the Emancipation Proclamation, which freed millions of slaves in 1863,[2] King observes that: one hundred years later, the Negro still is not free.[3] Toward the end of the speech, King departed from his prepared text for a partly improvised peroration on the theme I have a dream, prompted by Mahalia Jackson's cry: Tell them about the dream, Martin![4] In this part of the speech, which most excited the listeners and has now become its most famous, King described his dreams of freedom and equality arising from a land of slavery and hatred.[5] Jon Meacham writes that, With a single phrase, Martin Luther King, Jr. joined Jefferson and Lincoln in the ranks of men who've shaped modern America.[6] The speech was ranked the top American speech of the 20th century in a 1999 poll of scholars of public address.[7]"
      val codeTree = createCodeTree(input.toList)
      assert(input.toList == decode(codeTree, encode(codeTree)(input.toList)))
    }
  }

  test("Encode with create code tree: quick encode") {
    new TestTrees {
      val input = "Beginning with a reference to the Emancipation Proclamation, which freed millions of slaves in 1863,[2] King observes that: one hundred years later, the Negro still is not free.[3] Toward the end of the speech, King departed from his prepared text for a partly improvised peroration on the theme I have a dream, prompted by Mahalia Jackson's cry: Tell them about the dream, Martin![4] In this part of the speech, which most excited the listeners and has now become its most famous, King described his dreams of freedom and equality arising from a land of slavery and hatred.[5] Jon Meacham writes that, With a single phrase, Martin Luther King, Jr. joined Jefferson and Lincoln in the ranks of men who've shaped modern America.[6] The speech was ranked the top American speech of the 20th century in a 1999 poll of scholars of public address.[7]"
      val codeTree = createCodeTree(input.toList)
      assert(input.toList == decode(codeTree, quickEncode(codeTree)(input.toList)))
    }
  }
}
