"""
Assignment 1 - Weather Data Storage System
Course Code: ENCS205 / ENCA 201
Course: Data Structures
Semester: 3rd
Session: 2025-26

Problem:
Develop a Weather Data Storage System using Abstract Data Types (ADTs) and 2D arrays.
The system should:
1. Define a Weather Record ADT
2. Implement 2D array-based storage for city-wise, year-wise temperature
3. Support row-major and column-major access
4. Handle sparse data efficiently
5. Provide time and space complexity analysis
"""

# -----------------------------
# Weather Record ADT
# -----------------------------
class WeatherRecord:
    def _init_(self, date, city, temperature):
        self.date = date              # string (dd/mm/yyyy)
        self.city = city              # string
        self.temperature = temperature  # float

    def _str_(self):
        return f"{self.date} | {self.city} | {self.temperature}°C"


# -----------------------------
# Weather Data Storage System
# -----------------------------
class WeatherDataStorage:
    def _init_(self, years, cities):
        self.years = years
        self.cities = cities
        # Create a 2D array with None as sentinel for missing data
        self.data = [[None for _ in cities] for _ in years]

    # Insert data
    def populateArray(self, year, city, temperature):
        if year in self.years and city in self.cities:
            row = self.years.index(year)
            col = self.cities.index(city)
            self.data[row][col] = temperature

    # Row-major traversal
    def rowMajorAccess(self):
        print("\nRow Major Access:")
        for row in range(len(self.years)):
            for col in range(len(self.cities)):
                print(f"{self.years[row]} - {self.cities[col]}: {self.data[row][col]}")

    # Column-major traversal
    def columnMajorAccess(self):
        print("\nColumn Major Access:")
        for col in range(len(self.cities)):
            for row in range(len(self.years)):
                print(f"{self.years[row]} - {self.cities[col]}: {self.data[row][col]}")

    # Sparse data representation
    def handleSparseData(self):
        print("\nSparse Data Representation (Row, Col, Value):")
        sparse = []
        for row in range(len(self.years)):
            for col in range(len(self.cities)):
                if self.data[row][col] is not None:
                    sparse.append((row, col, self.data[row][col]))
        print(sparse)
        return sparse

    # Retrieve record
    def retrieve(self, city, year):
        if city in self.cities and year in self.years:
            row = self.years.index(year)
            col = self.cities.index(city)
            return self.data[row][col]
        return None

    # Time & space complexity analysis
    def analyzeComplexity(self):
        print("\n--- Complexity Analysis ---")
        print("Insert: O(1)")
        print("Retrieve: O(1)")
        print("Delete: O(1)")
        print("Row-major traversal: O(n*m)")
        print("Column-major traversal: O(n*m)")
        print("Sparse storage: O(k) where k = number of non-empty records")


# -----------------------------
# Example Driver Code
# -----------------------------
if _name_ == "_main_":
    # Define dataset
    years = [2023, 2024, 2025]
    cities = ["Delhi", "Mumbai", "Kolkata"]

    # Initialize storage
    storage = WeatherDataStorage(years, cities)

    # Insert sample data
    storage.populateArray(2023, "Delhi", 35.5)
    storage.populateArray(2023, "Mumbai", 29.2)
    storage.populateArray(2024, "Kolkata", 32.1)

    # Traversals
    storage.rowMajorAccess()
    storage.columnMajorAccess()

    # Sparse matrix
    storage.handleSparseData()

    # Retrieval
    print("\nRetrieve Delhi, 2023:", storage.retrieve("Delhi", 2023))

    # Complexity analysis
    storage.analyzeComplexity()
