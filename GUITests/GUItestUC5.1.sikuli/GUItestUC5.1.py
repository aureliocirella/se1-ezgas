click("1590859302006-1.png")
paste(Pattern("1590859335884-1.png").similar(0.49), "admin@ezgas.com")
type(Pattern("1590859815093-1.png").similar(0.54), "admin")
click("1590859853847-1.png")
click("1590859879732.png")
for n in range(20):
    click(Pattern("1590918091673.png").similar(0.62))
click("1590917363779.png")
for n in range(4):
    click("1590917217439.png")
 
paste(Pattern("1590929579967.png").targetOffset(28,31),"GasStation1")
paste(Pattern("1590916754119.png").similar(0.50).targetOffset(-195,0), "Via Roma Ciriè Piemont Italy")
paste(Pattern("1590916762871.png").targetOffset(-192,-1), "45.24123325")
paste(Pattern("1590916771413.png").similar(0.39).targetOffset(-180,0), "7.599452657309832")

find(Pattern("1590916824323.png").targetOffset(79,2)).click()
type("cartogo"+Key.ENTER)


find("1590917130836.png").click()
find("1590917147716.png").click()
find("1590917165322.png").click()
find("1590918018959.png").click()
pass