package com.gravitino.project.ldlockhart;

import org.apache.gravitino.Catalog;
import org.apache.gravitino.NameIdentifier;
import org.apache.gravitino.client.GravitinoAdminClient;
import org.apache.gravitino.client.GravitinoClient;
import org.apache.gravitino.client.GravitinoMetalake;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GravitinoLogisticsService {

    private final String GRAVITINO_URL = "http://localhost:8090";
    private final String METALAKE_NAME = "fedex_global";
    private final String CATALOG_NAME = "eu_iceberg_data";

    public String initializeLogisticsMetadata() {
        try {
            GravitinoAdminClient adminClient = GravitinoAdminClient.builder(GRAVITINO_URL).build();

            // 1. Create the Metalake
            GravitinoMetalake metalake = adminClient.createMetalake(
                    NameIdentifier.of(METALAKE_NAME),
                    "Global Logistics Metalake",
                    Map.of()
            );

            // 2. Define the strict properties required for a local Iceberg Lakehouse
            Map<String, String> icebergProperties = Map.of(
                    "catalog-backend", "jdbc",
                    "uri", "jdbc:h2:file:/tmp/gravitino/catalog_iceberg;DB_CLOSE_DELAY=-1;MODE=MYSQL",
                    "jdbc-user", "admin",
                    "jdbc-password", "admin",
                    "jdbc-driver", "org.h2.Driver",
                    "warehouse", "file:///tmp/gravitino/warehouse"
            );

            // 3. Create the Catalog with the valid Iceberg configuration
            Catalog catalog = metalake.createCatalog(
                    CATALOG_NAME,
                    Catalog.Type.RELATIONAL,
                    "lakehouse-iceberg",
                    "European Routing Data",
                    icebergProperties
            );

            // 4. Create the Schema natively
            catalog.asSchemas().createSchema(
                    "routing_schema",
                    "Schema for real-time European package routing",
                    Map.of()
            );

            return "Success! Created Metalake, Catalog, and Schema in local Gravitino.";

        } catch (Exception e) {
            e.printStackTrace(); // This prints the full error stack to your IDE console
            return "Integration Failed: " + e.getMessage();
        }
    }
}
