package co.edu.uniquindio.subastaq.subastaq.mapping.mappers;

import co.edu.uniquindio.subastaq.subastaq.mapping.dto.*;
import co.edu.uniquindio.subastaq.subastaq.model.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubastaMapper {
    SubastaMapper INSTANCE = Mappers.getMapper(SubastaMapper.class);

    //Mapping anunciantes
    @Mapping(target = "listaAnunciosDto", source = "listaAnuncios")
    @Named("AnuncianteToAnuncianteDto")
    AnuncianteDto anuncianteToAnuncianteDto(Anunciante anunciante);
    @Mapping(target = "listaAnuncios", source = "listaAnunciosDto")
    Anunciante anuncianteDtoToAnunciante(AnuncianteDto anuncianteDto);

    @IterableMapping(qualifiedByName = "AnuncianteToAnuncianteDto")
    List<AnuncianteDto> getAnunciantesDto(List<Anunciante> listaAnunciantes);
    @Mapping(target = "listaAnunciosDto", source = "listaAnuncios")
    @Named("mappingToAnuncianteDto")
    AnuncianteDto mappingToAnuncianteDto(Anunciante anunciante);

    //Mapping productos
    @Named("ProductoToProductoDto")
    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);
    @IterableMapping(qualifiedByName = "ProductoToProductoDto")
    List<ProductoDto> getProductosDto(List<Producto> listaProductos);
    @Named("mappingToProductoDto")
    ProductoDto mappingToProductoDto(Producto producto);

    //Mapping anuncio
    @Mapping(target = "productoDto", source = "producto")
    @Mapping(target = "listaPujasDto", source = "listaPujas")
    @Mapping(target = "anuncianteDto", source = "anunciante")
    @Mapping(target = "FechaLimite", source = "fechaLimite")
    @Named("AnuncioToAnuncioDto")
    AnuncioDto anuncioToAnuncioDto(Anuncio anuncio);
    @Mapping(target = "producto", source = "productoDto")
    @Mapping(target = "listaPujas", source = "listaPujasDto")
    @Mapping(target = "fechaLimite", source = "FechaLimite")
    @Mapping(target = "anunciante", source = "anuncianteDto")
    Anuncio anuncioDtoToAnuncio (AnuncioDto anuncioDto);
    @IterableMapping(qualifiedByName = "AnuncioToAnuncioDto")
    List<AnuncioDto> getAnunciosDto(List<Anuncio> listaAnuncios);
    @Mapping(target = "productoDto", source = "producto")
    @Mapping(target = "listaPujasDto", source = "listaPujas")
    @Mapping(target = "anuncianteDto", source = "anunciante")
    @Mapping(target = "FechaLimite", source = "fechaLimite")
    @Named("mappingToAnuncioDto")
    AnuncioDto mappingToAnuncioDto(Anuncio anuncio);

    //Mapping comprador
    @Mapping(target = "pujaDtoActual", source = "pujaActual")
    @Named("CompradorToCompradorDto")
    CompradorDto compradorToCompradorDto(Comprador comprador);
    @Mapping(target = "pujaActual", source = "pujaDtoActual")
    Comprador compradorDtoToComprador(CompradorDto compradorDto);
    @IterableMapping(qualifiedByName = "CompradorToCompradorDto")
    List<CompradorDto> getCompradoresDto(List<Comprador> listaCompradores);
    @Mapping(target = "pujaDtoActual", source = "pujaActual")
    @Named("mappingToCompradorDto")
    CompradorDto mappingToCompradorDto(Comprador comprador);

    //Mapping puja
    @Named("PujaToPujaDto")
    PujaDto pujaToPujaDto(Puja puja);
    Puja pujaDtoToPuja(PujaDto pujaDto);
    @IterableMapping(qualifiedByName = "PujaToPujaDto")
    List<PujaDto> getPujasDto(List<Puja> listaPujas);
    @Named("mappingToPujaDto")
    PujaDto mappingToPujaDto(Puja puja);

    //Mapping usuario
    @Named("UsuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto (Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
    @IterableMapping (qualifiedByName = "UsuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto (List<Usuario> listaUsuarios);
    @Named("mappingToUsuarioDto")
    UsuarioDto mappingToUsuarioDto(Usuario usuario);

}
