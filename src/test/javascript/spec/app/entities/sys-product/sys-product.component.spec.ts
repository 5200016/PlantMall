/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductComponent } from 'app/entities/sys-product/sys-product.component';
import { SysProductService } from 'app/entities/sys-product/sys-product.service';
import { SysProduct } from 'app/shared/model/sys-product.model';

describe('Component Tests', () => {
    describe('SysProduct Management Component', () => {
        let comp: SysProductComponent;
        let fixture: ComponentFixture<SysProductComponent>;
        let service: SysProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductComponent],
                providers: []
            })
                .overrideTemplate(SysProductComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysProductComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysProductService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysProduct(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
