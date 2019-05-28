/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingProductComponent } from 'app/entities/sys-shopping-product/sys-shopping-product.component';
import { SysShoppingProductService } from 'app/entities/sys-shopping-product/sys-shopping-product.service';
import { SysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

describe('Component Tests', () => {
    describe('SysShoppingProduct Management Component', () => {
        let comp: SysShoppingProductComponent;
        let fixture: ComponentFixture<SysShoppingProductComponent>;
        let service: SysShoppingProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingProductComponent],
                providers: []
            })
                .overrideTemplate(SysShoppingProductComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysShoppingProductComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingProductService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysShoppingProduct(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysShoppingProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
