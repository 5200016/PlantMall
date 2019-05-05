/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderProductDetailComponent } from 'app/entities/sys-order-product/sys-order-product-detail.component';
import { SysOrderProduct } from 'app/shared/model/sys-order-product.model';

describe('Component Tests', () => {
    describe('SysOrderProduct Management Detail Component', () => {
        let comp: SysOrderProductDetailComponent;
        let fixture: ComponentFixture<SysOrderProductDetailComponent>;
        const route = ({ data: of({ sysOrderProduct: new SysOrderProduct(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderProductDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysOrderProductDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysOrderProductDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysOrderProduct).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
