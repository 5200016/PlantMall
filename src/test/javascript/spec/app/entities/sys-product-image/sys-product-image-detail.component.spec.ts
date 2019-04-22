/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductImageDetailComponent } from 'app/entities/sys-product-image/sys-product-image-detail.component';
import { SysProductImage } from 'app/shared/model/sys-product-image.model';

describe('Component Tests', () => {
    describe('SysProductImage Management Detail Component', () => {
        let comp: SysProductImageDetailComponent;
        let fixture: ComponentFixture<SysProductImageDetailComponent>;
        const route = ({ data: of({ sysProductImage: new SysProductImage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductImageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysProductImageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysProductImageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysProductImage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
