import pandas as pd
import matplotlib.pyplot as plt

# Read the CSV file
df = pd.read_csv('sorting_durations.csv')

# Plotting
plt.figure(figsize=(10, 6))
for sorter in df['Sorter'].unique():
    subset = df[df['Sorter'] == sorter]
    plt.plot(subset['Duration'], label=sorter)

plt.xlabel('Iteration')
plt.ylabel('Duration (nanoseconds)')
plt.title('Sorting Duration Comparison')
plt.legend()
plt.show()
