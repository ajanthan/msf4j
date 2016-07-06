package org.wso2.msf4j.perftest.echo.springboot;/*
 * Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

/**
 * Resource class
 */
@Controller
@EnableAutoConfiguration
public class EchoService {

    @RequestMapping("/EchoService/echo")
    @ResponseBody
    String echo(@RequestBody String body) {
        return body;
    }

    @RequestMapping("/EchoService/dbecho")
    @ResponseBody
    public String fileWrite(@RequestBody String body) throws InterruptedException, IOException {
        String returnStr = "";
        java.nio.file.Path tempfile = Files.createTempFile(UUID.randomUUID().toString(), ".tmp");
        Files.write(tempfile, body.getBytes(Charset.defaultCharset()),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        returnStr = new String(Files.readAllBytes(tempfile), Charset.defaultCharset());
        Files.delete(tempfile);
        return returnStr;
    }
}
