package farid.louka.sqlite

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
	var myName = "Louka"
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		//its good to use try and catch with databases as things can go wrong
		//and you don't want your app to crash
		
		try {
		var myDatabase = this.openOrCreateDatabase("Musicians",Context.MODE_PRIVATE,null)
			
			myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT(2))")
//			myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)")
//			myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk', 20)")
//			myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Marina', 30)")
//			myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('George', 63)")
//          myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('$myName', 50)")
			
			
			myDatabase.execSQL("UPDATE musicians SET age = 70 WHERE name = 'Kirk'")
			myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Marina'")
			myDatabase.execSQL("UPDATE musicians SET name = 'Farid' WHERE name = 'Kirk'")
			
			val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)
			// you can also do that: val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE age > 50", null)
			// you can also do that to search for names starting
			// with a 'K': val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'", null)
			// you can also do that to search for names that has 'a' somewhere in
			// the word: val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%a%'", null)
			// you can also do that to search for names ending
			// with a 'K': val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%K'", null)
			
			
			val nameIndex = cursor.getColumnIndex("name")
			val ageIndex = cursor.getColumnIndex("age")
			cursor.moveToFirst()
			while (cursor!= null){
				println("Name: ${cursor.getString(nameIndex)}")
				println("Age: ${cursor.getInt(ageIndex)}")
				cursor.moveToNext()
			}
			cursor!!.close()
		}
		catch (e:Exception){
			e.printStackTrace()
		}
		
	}
}
