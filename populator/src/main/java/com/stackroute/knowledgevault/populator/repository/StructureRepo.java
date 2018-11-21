package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.Structure;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface StructureRepo extends Neo4jRepository<Structure,String> {
    @Query("MERGE (N:MT { type: {name} })")
    public Structure createMT(@Param("name") String name);
    @Query("MERGE (N:MTR { type: {name} }) return N")
    public void createMTR(@Param("name") String name);
    @Query("MATCH (a:MT { type: {node1} }),(b:MT { type: {node2} }),(c:MTR {type:{rel}}) MERGE (a)-[r1:links]->(c)-[r2:links]-(b) return r1,r2")
    public void createR(@Param("node1") String node1,@Param("rel") String rel,@Param("node2") String node2);
}
