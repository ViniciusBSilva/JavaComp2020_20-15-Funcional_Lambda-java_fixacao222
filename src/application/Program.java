package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.entities.Employee;

public class Program {

	public static void main(String[] args) {

		String filePath = "C:\\Temp\\java.csv";

		List<Employee> employees = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath)); 
				Scanner sc = new Scanner(System.in)) {

			String line = br.readLine();

			while (line != null) {
				String[] fields = new String[3];
				fields = line.split(",");
				employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Salary to filter: ");
			double salary = sc.nextDouble();
			
			Comparator<String> comp = (s1, s2) -> s1.compareToIgnoreCase(s2);
			
			List<String> emails = employees.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			System.out.println("Emails filtered: ");
			emails.forEach(System.out :: println);
			
			System.out.println();
			
			System.out.println(
					String.format("Sum of salary of people whose name starts with 'M':"
					+ " $ %.2f", employees.stream()
									.filter(p -> p.getName().charAt(0) == 'M')
									.map(p -> p.getSalary())
									.reduce(0.0, (x,y) -> x + y)  ) );
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
