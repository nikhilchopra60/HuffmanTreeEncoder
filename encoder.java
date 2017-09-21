package com.huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class encoder {
	
	
	static final String encoded = "encoded.bin";

	public static void main(String args[]){
	
		String input_file=args[0];
		
		
		long startTimebin   = System.currentTimeMillis();
		try {
			HuffmanCoding.build_freq_table(input_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//HuffmanCoding.build_tree_using_pairing_heap();
		//HuffmanCoding.build_tree_using_FourAry_heap();
		HuffmanCoding.build_tree_using_binary_heap();
		makeString(input_file);
		long endTimebin   = System.currentTimeMillis();
		long totalTimebin = endTimebin - startTimebin;
		System.out.println("Time using binary heap (millisecond): " +totalTimebin);
		
		
		
		
		
		long startTimepair   = System.currentTimeMillis();
		try {
			HuffmanCoding.build_freq_table(input_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HuffmanCoding.build_tree_using_pairing_heap();
		//HuffmanCoding.build_tree_using_FourAry_heap();
		//HuffmanCoding.build_tree_using_binary_heap();
		makeString(input_file);
		long endTimepair   = System.currentTimeMillis();
		long totalTimepair = endTimepair - startTimepair;
		System.out.println("Time using binary heap (millisecond): " +totalTimepair);
		
		
		
		
		
		long startTime4   = System.currentTimeMillis();
		try {
			HuffmanCoding.build_freq_table(input_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//HuffmanCoding.build_tree_using_pairing_heap();
		//HuffmanCoding.build_tree_using_FourAry_heap();
		//HuffmanCoding.build_tree_using_binary_heap();
		makeString(input_file);
		long endTime4   = System.currentTimeMillis();
		long totalTime4 = endTime4 - startTime4;
		System.out.println("Time using binary heap (millisecond): " +totalTime4);
		
		
		
		
		
		
	}
	
	
	
	
/*public static void writeToBinary(String inputFile){
	
	
	
	try {
		//String fileName ="C:\\Sem_2\\Advanced_Data_Structures\\Project\\encoded_new_pairing.bin";
		FileOutputStream fileOs;
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line = "";
		fileOs = new FileOutputStream(encoded);
		//ObjectOutputStream os= new ObjectOutputStream(fileOs);
		
		String code = "";
		String code_temp;
		while ((line = br.readLine()) != null) {
		  //  String a = Encode.encode(line2, hTree);
			line = line.trim();
			if(!line.equals("")){
				int val = Integer.valueOf(line);
				HashMapNode node = HuffmanCoding.hmap.get(val);
				code+=node.bits;
				
				while(code.length()>8){
					
					code_temp = code.substring(0, 8);
					code = code.substring(8);
					int parsedByte = 0XFF & Integer.parseInt(code_temp,2);
					//fileOs.write(parsedByte);
					fileOs.write(parsedByte);
										
				}
				
			}
					
		}
		if(!code.equals("")){
			int parsedByte = 0XFF & Integer.parseInt(code,2);
			fileOs.write(parsedByte);
			code = "";
		}
		fileOs.close();
		br.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
*/
	
	/* Make a string using codeTableHashmap after reading from input file */
	public static void makeString(String input_file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			StringBuilder code = new StringBuilder();
			String line = "";
			while((line = br.readLine()) != null && !line.equals("")) {
				
				line = line.trim();
				int val = Integer.valueOf(line);
				code.append(HuffmanCoding.hmap.get(val).bits);		
						
			}
			br.close();
			
			/* Write this fullCode string to encoded.bin file */
			encode(code.toString());
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found Exception makeString encoder: "+e.getMessage());
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception makeString encode: "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public static void encode(String code) throws IOException {
		FileOutputStream file_out = new FileOutputStream(encoded);
		int i = 0; 
		byte[] bytearray = new byte[code.length()/8];
		int count = 0;
		while(i < code.length() - 7){ 
			byte nextbyte = 0x00;
			for(int j = 0 ; j < 8; j++){
				nextbyte  = (byte) (nextbyte << 1);
				nextbyte += code.charAt(i+j) == '0'?0x0:0x1;
			}
			bytearray[count] = nextbyte;
			count++;
			i += 8;
		}
		file_out.write(bytearray);
	}
	
}
