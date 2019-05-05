/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderProductComponent } from 'app/entities/sys-order-product/sys-order-product.component';
import { SysOrderProductService } from 'app/entities/sys-order-product/sys-order-product.service';
import { SysOrderProduct } from 'app/shared/model/sys-order-product.model';

describe('Component Tests', () => {
    describe('SysOrderProduct Management Component', () => {
        let comp: SysOrderProductComponent;
        let fixture: ComponentFixture<SysOrderProductComponent>;
        let service: SysOrderProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderProductComponent],
                providers: []
            })
                .overrideTemplate(SysOrderProductComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysOrderProductComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysOrderProductService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysOrderProduct(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysOrderProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
