package com.my.iot.controller;

import com.my.iot.domain.Data;
import com.my.iot.domain.Gateway;
import com.my.iot.domain.Sensor;
import com.my.iot.service.GatewayService;
import com.my.iot.service.SensorService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private SensorService sensorService;

    /*-------------获取所有网关的xls表格--------------*/
    @GetMapping("/gateway")
    public String getGatewaysInfo(HttpServletResponse response) throws IOException {
        String filename = "gateway.xls";
        Workbook workbook = null;
        String msg;
        List<Gateway> gateways = gatewayService.findAll(false);
        try {
            response.setContentType("application/vnd.ms-excel");//xls文件的mimeType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = response.getOutputStream();
            workbook = this.createGatewaysWorkbook(gateways);
            workbook.write(out);
            out.flush();
            out.close();
            msg = "success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return msg;
    }

    //创建所有网关信息xls文档
    private Workbook createGatewaysWorkbook(List<Gateway> gateways) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 6 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        //设置居中加粗
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        String[] headers = new String[]{"ID", "ip", "端口", "描述", "位置"};
        HSSFRow headRow = sheet.createRow(0);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            HSSFCell cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        //添加信息
        for (int i = 1; i <= gateways.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            Gateway gateway = gateways.get(i - 1);
            row.createCell(0).setCellValue(gateway.getId());
            row.createCell(1).setCellValue(gateway.getIp());
            row.createCell(2).setCellValue(gateway.getPort());
            row.createCell(3).setCellValue(gateway.getDescription());
            row.createCell(4).setCellValue(gateway.getLocation());
        }
        return workbook;
    }

    /*-------------获取所有传感器的xls表格--------------*/
    @GetMapping("/sensor")
    public String getSensorsInfo(HttpServletResponse response) throws IOException {
        String filename = "sensor.xls";
        Workbook workbook = null;
        String msg;
        List<Sensor> sensors = sensorService.findAll();
        try {
            response.setContentType("application/vnd.ms-excel");//xls文件的mimeType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = response.getOutputStream();
            workbook = this.createSensorsWorkbook(sensors);
            workbook.write(out);
            out.flush();
            out.close();
            msg = "success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return msg;
    }

    //创建所有传感器信息xls文档
    private Workbook createSensorsWorkbook(List<Sensor> sensors) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 40 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        //设置居中加粗
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        //日期格式
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        String[] headers = new String[]{"ID", "描述", "位置", "厂家", "安装日期", "生产日期", "质保日期", "所属网关ID", "所属分类ID"};
        HSSFRow headRow = sheet.createRow(0);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            HSSFCell cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        //添加信息
        for (int i = 1; i <= sensors.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            Sensor sensor = sensors.get(i - 1);
            row.createCell(0).setCellValue(sensor.getId());
            row.createCell(1).setCellValue(sensor.getDescription());
            row.createCell(2).setCellValue(sensor.getLocation());
            row.createCell(3).setCellValue(sensor.getFactory());
            HSSFCell cell = row.createCell(4);
            cell.setCellValue(sensor.getInstall_time());
            cell.setCellStyle(dateStyle);
            cell = row.createCell(5);
            cell.setCellValue(sensor.getProduce_date());
            cell.setCellStyle(dateStyle);
            cell = row.createCell(6);
            cell.setCellValue(sensor.getMaintenance_time());
            cell.setCellStyle(dateStyle);
            row.createCell(7).setCellValue(sensor.getGate_id());
            row.createCell(8).setCellValue(sensor.getClassify_id());
        }
        return workbook;
    }

    /*-------------获取一个网关及其传感器的xls表格--------------*/
    @GetMapping("/gateway/{id}")
    public String getGatewayInfoByIdWithSensors(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        String filename = "gateway_id=" + id + ".xls";
        Workbook workbook = null;
        String msg;
        Gateway gateway = gatewayService.findByIdWithSensors(id);
        try {
            response.setContentType("application/vnd.ms-excel");//xls文件的mimeType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = response.getOutputStream();
            workbook = this.createOneGatewayWorkbookWithSensors(gateway);
            workbook.write(out);
            out.flush();
            out.close();
            msg = "success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return msg;
    }

    //创建一个网关及其所有传感器信息xls文档
    private Workbook createOneGatewayWorkbookWithSensors(Gateway gateway) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 50 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);

        //设置居中加粗
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        //日期格式
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        String[] headers = new String[]{"ID", "ip", "端口", "描述", "位置"};
        HSSFRow headRow = sheet.createRow(0);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            HSSFCell cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        //添加单个网关信息
        HSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue(gateway.getId());
        row.createCell(1).setCellValue(gateway.getIp());
        row.createCell(2).setCellValue(gateway.getPort());
        row.createCell(3).setCellValue(gateway.getDescription());
        row.createCell(4).setCellValue(gateway.getLocation());

        //传感器表头
        List<Sensor> sensors = gateway.getSensors();
        headers = new String[]{"传感器ID", "描述", "位置", "厂家", "安装日期", "生产日期", "质保日期", "所属网关ID", "所属分类ID"};
        headRow = sheet.createRow(3);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            HSSFCell cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        //添加网关下所有传感器信息
        for (int i = 1; i <= sensors.size(); i++) {
            row = sheet.createRow(i + 3);
            Sensor sensor = sensors.get(i - 1);
            row.createCell(0).setCellValue(sensor.getId());
            row.createCell(1).setCellValue(sensor.getDescription());
            row.createCell(2).setCellValue(sensor.getLocation());
            row.createCell(3).setCellValue(sensor.getFactory());
            HSSFCell cell = row.createCell(4);
            cell.setCellValue(sensor.getInstall_time());
            cell.setCellStyle(dateStyle);
            cell = row.createCell(5);
            cell.setCellValue(sensor.getProduce_date());
            cell.setCellStyle(dateStyle);
            cell = row.createCell(6);
            cell.setCellValue(sensor.getMaintenance_time());
            cell.setCellStyle(dateStyle);
            row.createCell(7).setCellValue(sensor.getGate_id());
            row.createCell(8).setCellValue(sensor.getClassify_id());
        }
        return workbook;
    }

    /*-------------获取一个传感器及其所有数据的xls表格--------------*/
    @GetMapping("/sensor/{id}")
    public String getSensorInfoByIdWithData(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        String filename = "sensor_id=" + id + ".xls";
        Workbook workbook = null;
        String msg;
        Sensor sensor = sensorService.findByIdWithDatas(id);
        try {
            response.setContentType("application/vnd.ms-excel");//xls文件的mimeType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = response.getOutputStream();
            workbook = this.createOneSensorWorkbookWithDatas(sensor);
            workbook.write(out);
            out.flush();
            out.close();
            msg = "success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return msg;
    }

    //创建所有传感器信息xls文档
    private Workbook createOneSensorWorkbookWithDatas(Sensor sensor) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 30 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        //设置居中加粗
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        //日期格式
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        String[] headers = new String[]{"ID", "描述", "位置", "厂家", "安装日期", "生产日期", "质保日期", "所属网关ID", "所属分类ID"};
        HSSFRow headRow = sheet.createRow(0);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            HSSFCell cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        //添加信息
        HSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue(sensor.getId());
        row.createCell(1).setCellValue(sensor.getDescription());
        row.createCell(2).setCellValue(sensor.getLocation());
        row.createCell(3).setCellValue(sensor.getFactory());
        HSSFCell cell = row.createCell(4);
        cell.setCellValue(sensor.getInstall_time());
        cell.setCellStyle(dateStyle);
        cell = row.createCell(5);
        cell.setCellValue(sensor.getProduce_date());
        cell.setCellStyle(dateStyle);
        cell = row.createCell(6);
        cell.setCellValue(sensor.getMaintenance_time());
        cell.setCellStyle(dateStyle);
        row.createCell(7).setCellValue(sensor.getGate_id());
        row.createCell(8).setCellValue(sensor.getClassify_id());

        //数据的表头
        headers = new String[]{"数据ID", "时间", "数值"};
        headRow = sheet.createRow(3);//表头行
        for (int j = 0; j < headers.length; j++) {//创建表头
            cell = headRow.createCell(j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(style);
        }

        HSSFCellStyle dateStyle2 = workbook.createCellStyle();
        dateStyle2.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));//excel的时间格式不能到秒
        // 添加数据
        List<Data> datas = sensor.getDatas();
        for (int i = 1; i <= datas.size(); i++) {
            Data data = datas.get(i - 1);
            row = sheet.createRow(i + 3);
            row.createCell(0).setCellValue(data.getId());
            cell = row.createCell(1);
            cell.setCellStyle(dateStyle2);
            cell.setCellValue(data.getTime());
            row.createCell(2).setCellValue(data.getData());
        }

        return workbook;
    }

    /*---------------------------------以下为测试程序----------------------------------*/

    @GetMapping("/test")
    public String getTestInfo(HttpServletResponse response) throws IOException {
        String filename = "test.xls";
        Workbook testWorkbook = null;
        String msg;
        try {
            response.setContentType("application/vnd.ms-excel");//xls文件的mimeType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = response.getOutputStream();
            testWorkbook = this.createTestWorkbook();
            testWorkbook.write(out);
            out.flush();
            out.close();
            msg = "success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        } finally {
            if (testWorkbook != null) {
                testWorkbook.close();
            }
        }
        return msg;
    }

    //创建测试用xls文档
    private Workbook createTestWorkbook() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headRow = sheet.createRow(0);//表头行
        //sheet.setColumnWidth(1,12*256);
        // sheet.setColumnWidth(1,17*256);

        //设置居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell header1 = headRow.createCell(0);
        header1.setCellValue("ID");
        header1.setCellStyle(style);
        HSSFCell header2 = headRow.createCell(1);
        header2.setCellValue("显示名");
        header2.setCellStyle(style);
        HSSFCell header3 = headRow.createCell(2);
        header3.setCellValue("用户名");
        header3.setCellStyle(style);

        for (int i = 1; i <= 5; i++) {//添加5行测试数据
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 3; j++) {
                row.createCell(j).setCellValue("测试" + i + "," + j);
            }
        }
        return workbook;
    }


}
