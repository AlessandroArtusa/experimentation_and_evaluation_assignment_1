import pandas as pd
import matplotlib.pyplot as plt

# Read the CSV file
df = pd.read_csv('sorting_durations.csv')

# Histograms for each sorting algorithm
sorting_algorithms = df['Sorter'].unique()
for algo in sorting_algorithms:
    subset = df[df['Sorter'] == algo]
    plt.figure(figsize=(12, 6))  # Further increase the size
    subset['Duration'].hist(bins=30)
    plt.title(f'Histogram of Durations for {algo}')
    plt.xlabel('Duration (nanoseconds)')
    plt.ylabel('Frequency')
    plt.grid(False)

# Box plot for each array type
array_types = df['Array order'].unique()
for array_type in array_types:
    subset = df[df['Array order'] == array_type]
    plt.figure(figsize=(14, 8))  # Further increase the size
    boxplot = subset.boxplot(column='Duration', by='Sorter')
    plt.title(f'Box Plot of Durations by Sorter for {array_type} Array')
    plt.suptitle('')
    plt.xlabel('Sorter')
    plt.ylabel('Duration (nanoseconds)')
    plt.xticks(rotation=90)  # Increase the angle of rotation
    plt.subplots_adjust(bottom=0.3)  # Adjust the bottom margin

plt.tight_layout()
plt.show()
