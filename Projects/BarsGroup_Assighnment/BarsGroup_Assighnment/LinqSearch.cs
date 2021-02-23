using NUnit.Framework;

namespace Tests
{
    [TestFixture]
    public class LinqSearch
    {
        [Test]
        public void verifyNumbersAreEqual () {
            int num = 0;
            int next = 0;

            Assert.AreEqual(num, next);
        }

        [Test]
        public void verifyWordsAreEqual () {
            string suz = "Ruzal";
            string bukva = "k";

            Assert.IsTrue(suz.Contains(bukva));
        }
    }


}


